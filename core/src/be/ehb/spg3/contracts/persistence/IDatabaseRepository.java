package be.ehb.spg3.contracts.persistence;

// Created by Wannes Gennar. All rights reserved

public interface IDatabaseRepository
{
	void initialize();

	void finish();

	<T> void createOrUpdate(T model);
}
