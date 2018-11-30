package com.bw.movie.bean;

/**
 * @ProjectName: Movie
 * @Package: com.bw.movie.bean
 * @ClassName: RegisterBean
 * @Description: java类作用描述
 * @Author: Jack Lee
 * @CreateDate: 2018/10/17 14:30
 * @UpdateUser: Jack Lee
 * @UpdateDate: 2018/10/17 14:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RegisterBean {


    /**
     * message : 注册成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
