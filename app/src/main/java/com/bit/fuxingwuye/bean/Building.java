package com.bit.fuxingwuye.bean;

import java.util.List;

/**
 * Created by 23 on 2018/2/25.
 */

public class Building {
    /**
     * currentPage : 1
     * records : [{"code":"1","communityId":"5a9157839ce95a94f49e6ea4","coordinate":null,"createAt":1519475380282,"createId":null,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eae","miliBId":136,"name":"1号楼","rank":null,"updateAt":1519475380282},{"code":"2","communityId":"5a9157839ce95a94f49e6ea4","coordinate":null,"createAt":1519475380350,"createId":null,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eb0","miliBId":137,"name":"2号楼","rank":null,"updateAt":1519475380350},{"code":"3","communityId":"5a9157839ce95a94f49e6ea4","coordinate":null,"createAt":1519475380400,"createId":null,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eb2","miliBId":138,"name":"3号楼","rank":null,"updateAt":1519475380400},{"code":"4","communityId":"5a9157839ce95a94f49e6ea4","coordinate":null,"createAt":1519475380449,"createId":null,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eb4","miliBId":155,"name":"A4栋","rank":null,"updateAt":1519475380449}]
     * total : 4
     * totalPage : 1
     */


    /**
     *{"currentPage":1,"records":[{"code":"1","communityId":"5a9157839ce95a94f49e6ea4","createAt":1519475380282,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eae","miliBId":136,"name":"1号楼","rank":0,"updateAt":1519475380282},{"code":"2","communityId":"5a9157839ce95a94f49e6ea4","createAt":1519475380350,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eb0","miliBId":137,"name":"2号楼","rank":0,"updateAt":1519475380350},{"code":"3","communityId":"5a9157839ce95a94f49e6ea4","createAt":1519475380400,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eb2","miliBId":138,"name":"3号楼","rank":0,"updateAt":1519475380400},{"code":"4","communityId":"5a9157839ce95a94f49e6ea4","createAt":1519475380449,"dataStatus":1,"id":"5a915ab49ce95a94f49e6eb4","miliBId":155,"name":"A4栋","rank":0,"updateAt":1519475380449}],"total":4,"totalPage":1}

     */
    private int currentPage;
    private int total;
    private int totalPage;
    private List<RecordsBean> records;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * code : 1
         * communityId : 5a9157839ce95a94f49e6ea4
         * coordinate : null
         * createAt : 1519475380282
         * createId : null
         * dataStatus : 1
         * id : 5a915ab49ce95a94f49e6eae
         * miliBId : 136
         * name : 1号楼
         * rank : null
         * updateAt : 1519475380282
         */

        private String code;
        private String communityId;
        private String coordinate;
        private long createAt;
        private String createId;
        private int dataStatus;
        private String id;
        private int miliBId;
        private String name;
        private int rank;
        private long updateAt;

        public String getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(String coordinate) {
            this.coordinate = coordinate;
        }

        public String getCreateId() {
            return createId;
        }

        public void setCreateId(String createId) {
            this.createId = createId;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCommunityId() {
            return communityId;
        }

        public void setCommunityId(String communityId) {
            this.communityId = communityId;
        }




        public long getCreateAt() {
            return createAt;
        }

        public void setCreateAt(long createAt) {
            this.createAt = createAt;
        }





        public int getDataStatus() {
            return dataStatus;
        }

        public void setDataStatus(int dataStatus) {
            this.dataStatus = dataStatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getMiliBId() {
            return miliBId;
        }

        public void setMiliBId(int miliBId) {
            this.miliBId = miliBId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



        public long getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(long updateAt) {
            this.updateAt = updateAt;
        }
    }
}
