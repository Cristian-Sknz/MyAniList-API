package me.skiincraft.mal.api;

/**
 * <h1>Request</h1>
 * <p>This interface serves only to indicate that a Request is being made.</p>
 *
 * @param <T> Requested object
 */
@FunctionalInterface
public interface Request<T> {
	
	T get();

}
