package com.example.testkladrapi.kladrAPI.buildJsonModel;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("zip")
	private int zip;

	@SerializedName("typeShort")
	private String typeShort;

	@SerializedName("ifnsul")
	private String ifnsul;

	@SerializedName("type")
	private String type;

	@SerializedName("okato")
	private String okato;

	@SerializedName("oktmo")
	private String oktmo;

	@SerializedName("ifnsfl")
	private String ifnsfl;

	@SerializedName("cadnum")
	private String cadnum;

	@SerializedName("parentGuid")
	private String parentGuid;

	@SerializedName("name")
	private String name;

	@SerializedName("guid")
	private String guid;

	@SerializedName("id")
	private String id;

	@SerializedName("contentType")
	private String contentType;

	public void setZip(int zip){
		this.zip = zip;
	}

	public int getZip(){
		return zip;
	}

	public void setTypeShort(String typeShort){
		this.typeShort = typeShort;
	}

	public String getTypeShort(){
		return typeShort;
	}

	public void setIfnsul(String ifnsul){
		this.ifnsul = ifnsul;
	}

	public String getIfnsul(){
		return ifnsul;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setOkato(String okato){
		this.okato = okato;
	}

	public String getOkato(){
		return okato;
	}

	public void setOktmo(String oktmo){
		this.oktmo = oktmo;
	}

	public String getOktmo(){
		return oktmo;
	}

	public void setIfnsfl(String ifnsfl){
		this.ifnsfl = ifnsfl;
	}

	public String getIfnsfl(){
		return ifnsfl;
	}

	public void setCadnum(String cadnum){
		this.cadnum = cadnum;
	}

	public String getCadnum(){
		return cadnum;
	}

	public void setParentGuid(String parentGuid){
		this.parentGuid = parentGuid;
	}

	public String getParentGuid(){
		return parentGuid;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getGuid(){
		return guid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}
}