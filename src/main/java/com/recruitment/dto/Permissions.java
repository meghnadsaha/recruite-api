package com.recruitment.dto;

import lombok.Data;

import java.util.List;

@Data
public class Permissions {

    private List<ModulePermission> modulePermissions;
    private SocialPermissions socialPermissions;

    @Data
    public static class ModulePermission {
        private String entity;
        private boolean tabVisible;
        private boolean view;
        private boolean create;
        private boolean edit;
        private boolean delete;
    }

    @Data
    public static class SocialPermissions {
        private boolean socialAdmin;
        private boolean managePosts;
        private boolean viewAnalytics;
    }
}
