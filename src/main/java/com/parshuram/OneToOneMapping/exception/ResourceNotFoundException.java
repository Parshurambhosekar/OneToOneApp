package com.parshuram.OneToOneMapping.exception;

public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private String fieldValue;
    private Integer value;

    private String name;

    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue){
        super(String.format("%s is not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;

    }

    public ResourceNotFoundException(String resourceName,String fieldName,Integer value){
        super(String.format("%s is not found with %s : %s",resourceName,fieldName,value));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.value=value;

    }

    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue,String name){
        super(String.format("%s is not found with %s : %s, %s",resourceName,fieldName,fieldValue,name));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
        this.name=name;

    }

}
