package com.wmt.hardip.ui.userList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wmt.hardip.data.model.BaseBean;

import java.util.ArrayList;

public class UserModel extends BaseBean {

    public UserModel.dataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(UserModel.dataObject dataObject) {
        this.dataObject = dataObject;
    }

    @SerializedName("data")
    @Expose
    dataObject dataObject;

    public class dataObject {

        public ArrayList<UsersArray> getUsersArrays() {
            return usersArrays;
        }

        public void setUsersArrays(ArrayList<UsersArray> usersArrays) {
            this.usersArrays = usersArrays;
        }

        @SerializedName("users")
        @Expose
        ArrayList<UsersArray> usersArrays = new ArrayList<>();

        public class UsersArray {

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            @SerializedName("id")
            @Expose
            int id;
            @SerializedName("username")
            @Expose
            String username;
            @SerializedName("email")
            @Expose
            String email;
            @SerializedName("profile_pic")
            @Expose
            String profile_pic;

        }

        public PaginationObj getPaginationObj() {
            return paginationObj;
        }

        public void setPaginationObj(PaginationObj paginationObj) {
            this.paginationObj = paginationObj;
        }

        @SerializedName("pagination")
        @Expose
        PaginationObj paginationObj;

        public class PaginationObj {

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            @SerializedName("total")
            @Expose
            int total;

            @SerializedName("page")
            @Expose
            int page;

            @SerializedName("lastPage")
            @Expose
            int lastPage;
        }

    }

}
