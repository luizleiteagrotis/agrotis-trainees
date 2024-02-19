package com.agrotis.trainees.crud.repository.wrapper;

public class EntityNotFoundException extends JpaRepositoryWrapperException{

	public EntityNotFoundException(String message) {
		super(message);
	}
}
