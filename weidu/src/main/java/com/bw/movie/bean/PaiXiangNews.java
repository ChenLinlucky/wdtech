package com.bw.movie.bean;

public class PaiXiangNews {

    /**
     * result : {"address":"朝阳区广顺北大街16号望京华彩商业中心B1","businessHoursContent":"周一至周五09:30-23:00；周末09:00-23:30","commentTotal":0,"distance":0,"followCinema":true,"id":10,"logo":"http://172.17.8.100/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院","phone":"18810211987","vehicleRoute":"线路1：乘公交运通110、运通111、运通113、运通117、专112路、311路、404路、409路、416路、422路、660路、851路、855路、913路、939路、944路、966路至利泽中街西口下车即是 线路2：地铁14号线东湖渠站，东北口出"}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * address : 朝阳区广顺北大街16号望京华彩商业中心B1
         * businessHoursContent : 周一至周五09:30-23:00；周末09:00-23:30
         * commentTotal : 0
         * distance : 0
         * followCinema : true
         * id : 10
         * logo : http://172.17.8.100/images/movie/logo/hyxd.jpg
         * name : 华谊兄弟影院
         * phone : 18810211987
         * vehicleRoute : 线路1：乘公交运通110、运通111、运通113、运通117、专112路、311路、404路、409路、416路、422路、660路、851路、855路、913路、939路、944路、966路至利泽中街西口下车即是 线路2：地铁14号线东湖渠站，东北口出
         */

        private String address;
        private String businessHoursContent;
        private int commentTotal;
        private int distance;
        private boolean followCinema;
        private int id;
        private String logo;
        private String name;
        private String phone;
        private String vehicleRoute;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessHoursContent() {
            return businessHoursContent;
        }

        public void setBusinessHoursContent(String businessHoursContent) {
            this.businessHoursContent = businessHoursContent;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public boolean isFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(boolean followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVehicleRoute() {
            return vehicleRoute;
        }

        public void setVehicleRoute(String vehicleRoute) {
            this.vehicleRoute = vehicleRoute;
        }
    }
}
