package com.example.testkladrapi.kladrAPI.models;

public class BuildModel {
    String buildName;

    public BuildModel(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    @Override
    public String toString() {
        return buildName;
    }
}
