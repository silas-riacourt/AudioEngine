package fr.audioengine.model;

public class Audio {

	private String name;
	private String path;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public Audio(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}
	
	
}
