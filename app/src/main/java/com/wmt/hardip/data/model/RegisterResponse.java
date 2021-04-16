package com.wmt.hardip.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterResponse extends BaseBean {

    public DataObj getDataObj() {
        return dataObj;
    }

    public void setDataObj(DataObj dataObj) {
        this.dataObj = dataObj;
    }

    @SerializedName("data")
    @Expose
    DataObj dataObj;

    public class DataObj implements Serializable {

        public UserObj getUserObj() {
            return userObj;
        }

        public void setUserObj(UserObj userObj) {
            this.userObj = userObj;
        }

        @SerializedName("user")
        @Expose
        UserObj userObj;

        public class UserObj implements Serializable{

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            @SerializedName("id")
            @Expose
            int id;
            @SerializedName("email")
            @Expose
            String email;
            @SerializedName("username")
            @Expose
            String username;

        }

        public DataObj.TokenObj getTokenObj() {
            return tokenObj;
        }

        public void setTokenObj(DataObj.TokenObj tokenObj) {
            this.tokenObj = tokenObj;
        }

        @SerializedName("token")
        @Expose
        TokenObj tokenObj;

        public class TokenObj implements Serializable{

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            @SerializedName("type")
            @Expose
            String type;

            @SerializedName("token")
            @Expose
            String token;

        }
    }
}
