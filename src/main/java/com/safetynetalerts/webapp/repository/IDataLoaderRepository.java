package com.safetynetalerts.webapp.repository;

import com.safetynetalerts.webapp.model.Response;

import java.io.IOException;

public interface IDataLoaderRepository {

    public Response getResponse() throws IOException;

}
