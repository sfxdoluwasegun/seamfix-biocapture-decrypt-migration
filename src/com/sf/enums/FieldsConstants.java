/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.enums;

/**
 *
 * @author DAWUZI
 */
public enum FieldsConstants
{
  FIELD_UNIQUE_NUMBER("uniqueNumber", true), 
  FIELD_SURNAME("surname", true), 
  FIELD_FIRST_NAME("firstname", true), 
  FIELD_OTHER_NAME("othername", true), 
  FIELD_GENDER("gender", true), 
  FIELD_DATE_OF_BIRTH("dateOfBirth", true), 
  FIELD_MOBILE_NUMBER("mobileNumber", true), 
  FIELD_EMAIL("email", true), 
  FIELD_MARITAL_STATUS("maritalStatus", true), 
  FIELD_TITLE("title", true), 
  FIELD_NATIONALlITY("nationality", true), 
  FIELD_LGA("lga", true), 
  FIELD_STATE("state", true), 
  FIELD_ADDRESS("address", true), 
  FIELD_RELIGION("religion", true), 

  FIELD_DA1("da1", false), 
  FIELD_DA2("da2", false), 
  FIELD_DA3("da3", false), 
  FIELD_DA4("da4", false), 
  FIELD_DA5("da5", false), 
  FIELD_DA6("da6", false), 
  FIELD_DA7("da7", false), 
  FIELD_DA8("da8", false), 
  FIELD_DA9("da9", false), 
  FIELD_DA10("da10", false), 

  FIELD_DA11("da11", false), 
  FIELD_DA12("da12", false), 
  FIELD_DA13("da13", false), 
  FIELD_DA14("da14", false), 
  FIELD_DA15("da15", false), 
  FIELD_DA16("da16", false), 
  FIELD_DA17("da17", false), 
  FIELD_DA18("da18", false), 
  FIELD_DA19("da19", false), 
  FIELD_DA20("da20", false), 

  FIELD_DA21("da21", false), 
  FIELD_DA22("da22", false), 
  FIELD_DA23("da23", false), 
  FIELD_DA24("da24", false), 
  FIELD_DA25("da25", false), 
  FIELD_DA26("da26", false), 
  FIELD_DA27("da27", false), 
  FIELD_DA28("da28", false), 
  FIELD_DA29("da29", false), 
  FIELD_DA30("da30", false), 

  FIELD_DA31("da31", false), 
  FIELD_DA32("da32", false), 
  FIELD_DA33("da33", false), 
  FIELD_DA34("da34", false), 
  FIELD_DA35("da35", false), 
  FIELD_DA36("da36", false), 
  FIELD_DA37("da37", false), 
  FIELD_DA38("da38", false), 
  FIELD_DA39("da39", false), 
  FIELD_DA40("da40", false), 

  FIELD_DDA1("dda1", false), 
  FIELD_DDA2("dda2", false), 
  FIELD_DDA3("dda3", false), 
  FIELD_DDA4("dda4", false), 
  FIELD_DDA5("dda5", false), 
  FIELD_DDA6("dda6", false), 
  FIELD_DDA7("dda7", false), 
  FIELD_DDA8("dda8", false), 
  FIELD_DDA9("dda9", false), 
  FIELD_DDA10("dda10", false), 

  FIELD_DDA11("dda11", false), 
  FIELD_DDA12("dda12", false), 
  FIELD_DDA13("dda13", false), 
  FIELD_DDA14("dda14", false), 
  FIELD_DDA15("dda15", false), 
  FIELD_DDA16("dda16", false), 
  FIELD_DDA17("dda17", false), 
  FIELD_DDA18("dda18", false), 
  FIELD_DDA19("dda19", false), 
  FIELD_DDA20("dda20", false);

  private String fieldName;
  private boolean basicData;

  private FieldsConstants(String fieldName, boolean basicData) { this.basicData = basicData;
    this.fieldName = fieldName; }


  public boolean isBasicData()
  {
    return this.basicData;
  }
  public String getFieldName() {
    return this.fieldName;
  }

  public static FieldsConstants fromValue(String fieldName) {
    if (fieldName == null) {
      throw new IllegalArgumentException("fieldName is null");
    }
    for (FieldsConstants fc : values()) {
      if (fc.getFieldName().trim().equalsIgnoreCase(fieldName.trim())) {
        return fc;
      }
    }
    return null;
  }
}