package mrhao.com.dailyproject2.bean;

import java.util.List;

public class MaJiaUrlBean {


    /**
     * code : 200
     * message : 获取成功
     * data : [{"id":"12","type":"1","url":"https://api.koudaihk.com:4432/api/info/shareDetailsMC.html?objectId=1014&channelId=1207","name":"travel"},{"id":"13","type":"0","url":"https://mparticle.uc.cn/article.html?uc_param_str=frdnsnpfvecpntnwprdssskt&wm_aid=0691473850ce44928b9a891bcb71de76&wm_id=2c062ace34d345d28561f1a62531513c","name":"sport"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;


    @Override
    public String toString() {
        return "MaJiaUrlBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 12
         * type : 1
         * url : https://api.koudaihk.com:4432/api/info/shareDetailsMC.html?objectId=1014&channelId=1207
         * name : travel
         */

        private String id;
        private String type;
        private String url;
        private String name;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
