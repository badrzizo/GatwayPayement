package com.javaproject.stripepayementgateway.dto;

public class StripeResponse {
    private String status;
    private String message;
    private String session_id;
    private String session_url;

    // Constructeur privé pour le builder
    private StripeResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.session_id = builder.session_id;
        this.session_url = builder.session_url;
    }

    // Getter
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getSession_url() {
        return session_url;
    }

    // Méthode statique pour obtenir une instance du builder
    public static Builder builder() {
        return new Builder();
    }

    // Classe Builder
    public static class Builder {
        private String status;
        private String message;
        private String session_id;
        private String session_url;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder session_id(String session_id) {
            this.session_id = session_id;
            return this;
        }

        public Builder session_url(String session_url) {
            this.session_url = session_url;
            return this;
        }

        public StripeResponse build() {
            return new StripeResponse(this);
        }
    }
}