package com.janaka.projects.services.business.common;

import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public abstract class BusinessService {

  protected void doWork(UnitOfWork uow) {
    uow.execute();
  }

}
