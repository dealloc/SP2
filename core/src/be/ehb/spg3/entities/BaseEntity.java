package be.ehb.spg3.entities;

// Created by Wannes Gennar. All rights reserved

/**
 * Base class for all entities. This is used for building abstract queries based on primary keys.
 */
public abstract class BaseEntity
{
	private long id;

	/**
	 * Get the primary index for this model
	 *
	 * @return The identifier of this model.
	 */
	public long getId()
	{
		return id;
	}
}
