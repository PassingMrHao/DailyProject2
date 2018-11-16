package mrhao.com.dailyproject2.bean;

public class TravelAllJingDianDateBean {


    /**
     * id : 4207
     * name : 大三巴牌坊
     * attraction_trips_count : 442
     * user_score : 4.1
     * description : 位于炮台山下，是澳门最具代表性的“澳门八景”之一，为1580年殖民时期竣工的圣保禄大教堂的前壁。教堂的其他部分毁于两次火灾，只剩下了今天的大三巴牌坊。
     * name_en : Ruins of St Paul s Cathedral
     * attraction_type : null
     * description_summary : 位于炮台山下，是澳门最具代表性的“澳门八景”之一，为1580年殖民时期竣工的圣保
     * image_url : http://m.chanyouji.cn/attractions/4207.jpg
     */

    private int id;
    private String name;
    private int attraction_trips_count;
    private String user_score;
    private String description;
    private String name_en;
    private Object attraction_type;
    private String description_summary;
    private String image_url;

    @Override
    public String toString() {
        return "TravelAllJingDianDateBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attraction_trips_count=" + attraction_trips_count +
                ", user_score='" + user_score + '\'' +
                ", description='" + description + '\'' +
                ", name_en='" + name_en + '\'' +
                ", attraction_type=" + attraction_type +
                ", description_summary='" + description_summary + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Object getAttraction_type() {
        return attraction_type;
    }

    public void setAttraction_type(Object attraction_type) {
        this.attraction_type = attraction_type;
    }

    public String getDescription_summary() {
        return description_summary;
    }

    public void setDescription_summary(String description_summary) {
        this.description_summary = description_summary;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

