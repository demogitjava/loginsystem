package de.jsoft.service;


import de.jsoft.dao.LoadUsersToMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
  @Autowired
  LoadUsersToMemory lusertomem;


    public LoadUsersToMemory getLusertomem() {
        return lusertomem;
    }

    public void setLusertomem(LoadUsersToMemory lusertomem) {
        this.lusertomem = lusertomem;
    }
}
