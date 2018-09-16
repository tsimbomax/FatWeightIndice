package com.example.root.fatweightindice.dao;

import com.example.root.fatweightindice.bean.Profile;

import java.util.List;

public interface ProfileDAO {

    public void saveProfile(Object profile) throws DAOException;

    public Object getLastProfile() throws DAOException;

    public List<Profile> getProfiles() throws DAOException;
}
