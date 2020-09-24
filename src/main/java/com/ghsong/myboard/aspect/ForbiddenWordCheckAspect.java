package com.ghsong.myboard.aspect;

import com.ghsong.myboard.advice.exception.CForbiddenWorkException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class ForbiddenWordCheckAspect {

    // 어노테이션이 설정된 메소드가 실행되지 직전(before)에 실행되는 로직
    @Before(value = "@annotation(forbiddenWordCheck)")
    public void forbiddenWordCheck(JoinPoint joinPoint, ForbiddenWordCheck forbiddenWordCheck) throws IllegalAccessException, NoSuchFieldException {
        // 금칙어를 체크할 메서드의 파라미터가 객체인지(객체, 필드명) 일반 String 인지에 따라 구분처리
        String[] param = forbiddenWordCheck.param().split("\\.");
        String paramName = "";
        String fieldName = "";
        if (param.length == 2) {
            paramName = param[0];
            fieldName = param[1];
        } else {
            fieldName = forbiddenWordCheck.param();
        }

        // 파라미터 이름으로 메서드의 몇번째 파라미터인지 구한다.
        Integer parameterIdx = getParameterIdx(joinPoint, paramName);
        if (parameterIdx == -1)
            throw new IllegalArgumentException();

        String checkWord;
        // 금칙어 체크할 문장을 객체내의 필드값에서 알아내야 할 경우(리플렉션 이용)
        if (!StringUtils.isEmpty(fieldName)) {
            Class<?> clazz = forbiddenWordCheck.checkClazz();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            checkWord = (String) field.get(joinPoint.getArgs()[parameterIdx]);
        } else {
            // 금칙어 체크할 문장이 String 형태로 넘어오는 경우
            checkWord = (String) joinPoint.getArgs()[parameterIdx];
        }

        // 체크할 문장에 금칙어가 포함되어 있는지 체크
        checkForbiddenWord(checkWord);
    }

    // 입력된 문장에 금칙어가 포함되어 있으면 Exception 발생
    private void checkForbiddenWord(String word) {
        List<String> forbiddenWords = Arrays.asList("바보","멍청이");

        Optional<String> forbiddenWord = forbiddenWords.stream().filter(word::contains).findFirst();
        if (forbiddenWord.isPresent())
            throw new CForbiddenWorkException(forbiddenWord.get());
    }

    // 메서드의 파라미터 이름으로 몇번째 파라미터인지 구한다.
    private Integer getParameterIdx(JoinPoint joinPoint, String paramName) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            String parameterName = parameterNames[i];
            if (paramName.equals(parameterName)) {
                return i;
            }
        }
        return -1;
    }

}
