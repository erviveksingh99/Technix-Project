package com.technix.dto;

public class Views {
    // This view will include only the parent category and exclude the child categories
    public static class ParentView {}

    // This view could be used to include child categories as well
    public static class ChildView extends ParentView {}

}

