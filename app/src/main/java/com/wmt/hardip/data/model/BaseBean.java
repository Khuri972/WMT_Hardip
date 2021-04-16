package com.wmt.hardip.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseBean implements Serializable {

    public MetaObj getMetaObj() {
        return metaObj;
    }

    public void setMetaObj(MetaObj metaObj) {
        this.metaObj = metaObj;
    }

    @SerializedName("meta")
    @Expose
   public MetaObj metaObj;

    public class MetaObj implements Serializable {

        @SerializedName("status")
        public String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @SerializedName("message")
        public String message;

        public boolean isSuccess() {
            return status.equalsIgnoreCase("ok");
        }
    }

}
