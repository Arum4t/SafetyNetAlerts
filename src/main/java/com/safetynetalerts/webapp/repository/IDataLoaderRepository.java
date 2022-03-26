package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Response;

import java.io.IOException;

public interface IDataLoaderRepository {

    Response getResponse() throws IOException;

}
