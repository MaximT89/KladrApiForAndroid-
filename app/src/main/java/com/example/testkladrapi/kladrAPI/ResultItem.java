package com.example.testkladrapi.kladrAPI;

import java.util.List;

public class ResultItem{
	private Object zip;
	private String typeShort;
	private String name;
	private String id;
	private String type;
	private String okato;
	private List<ParentsItem> parents;
	private String ifnsul;
	private String oktmo;
	private String cadnum;
	private String ifnsfl;
	private String parentGuid;
	private String guid;

	public void setZip(Object zip){
		this.zip = zip;
	}

	public Object getZip(){
		return zip;
	}

	public void setTypeShort(String typeShort){
		this.typeShort = typeShort;
	}

	public String getTypeShort(){
		return typeShort;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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

	public void setParents(List<ParentsItem> parents){
		this.parents = parents;
	}

	public List<ParentsItem> getParents(){
		return parents;
	}

	public void setIfnsul(String ifnsul){
		this.ifnsul = ifnsul;
	}

	public String getIfnsul(){
		return ifnsul;
	}

	public void setOktmo(String oktmo){
		this.oktmo = oktmo;
	}

	public String getOktmo(){
		return oktmo;
	}

	public void setCadnum(String cadnum){
		this.cadnum = cadnum;
	}

	public String getCadnum(){
		return cadnum;
	}

	public void setIfnsfl(String ifnsfl){
		this.ifnsfl = ifnsfl;
	}

	public String getIfnsfl(){
		return ifnsfl;
	}

	public void setParentGuid(String parentGuid){
		this.parentGuid = parentGuid;
	}

	public String getParentGuid(){
		return parentGuid;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getGuid(){
		return guid;
	}
}