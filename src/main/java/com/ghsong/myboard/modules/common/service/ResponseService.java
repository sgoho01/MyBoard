package com.ghsong.myboard.modules.common.service;

import com.ghsong.myboard.config.response.CommonResponse;
import com.ghsong.myboard.config.response.CommonResult;
import com.ghsong.myboard.config.response.ListResult;
import com.ghsong.myboard.config.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    /**
     * 단일건 결과 처리
     *
     * @param data
     * @param <T>
     * @return
     */
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    /**
     * 다중건 결과 처리
     *
     * @param list
     * @param <T>
     * @return
     */
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    /**
     * 성공 결과만 처리
     *
     * @return
     */
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    /**
     * 실패 결과만 처리
     *
     * @return
     */
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    /**
     * 결과에 성공 결과 세팅
     *
     * @param result
     */
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

}
