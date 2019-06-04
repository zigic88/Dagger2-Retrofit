package com.reja.daggerretrofit.model;

import com.squareup.moshi.Json;

public class Category_ {

@Json(name = "categories")
private Categories categories;

public Categories getCategories() {
return categories;
}

public void setCategories(Categories categories) {
this.categories = categories;
}

}