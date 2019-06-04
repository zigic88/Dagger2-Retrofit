package com.reja.daggerretrofit.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Category {

@Json(name = "categories")
private List<Category_> categories = null;

public List<Category_> getCategories() {
return categories;
}

public void setCategories(List<Category_> categories) {
this.categories = categories;
}

}