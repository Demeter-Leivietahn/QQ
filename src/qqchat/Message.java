package qqchat;

import java.awt.Color;
import java.io.Serializable;

public class Message implements Serializable{


	

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the startX
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * @param startX the startX to set
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * @return the startY
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * @param startY the startY to set
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}

	/**
	 * @return the endX
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * @param endX the endX to set
	 */
	public void setEndX(int endX) {
		this.endX = endX;
	}

	/**
	 * @return the endY
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * @param endY the endY to set
	 */
	public void setEndY(int endY) {
		this.endY = endY;
	}

	private String messgaeType;
	private String messgae;
	private String owner;
	private String getter;
	private  byte[] by;
	private String filename;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private Color color;
	private int size;
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the by
	 */
	public byte[] getBy() {
		return by;
	}

	/**
	 * @param by the by to set
	 */
	public void setBy(byte[] by) {
		this.by = by;
	}

	/**
	 * @return the messgaeType
	 */
	public String getMessgaeType() {
		return messgaeType;
	}

	public Message() {
		
	}

	public Message(String messgaeType, String messgae, String owner, String getter) {
		super();
		this.messgaeType = messgaeType;
		this.messgae = messgae;
		this.owner = owner;
		this.getter = getter;
	}

	/**
	 * @param messgaeType
	 *            the messgaeType to set
	 */
	public void setMessgaeType(String messgaeType) {
		this.messgaeType = messgaeType;
	}

	/**
	 * @return the messgae
	 */
	public String getMessgae() {
		return messgae;
	}

	/**
	 * @param messgae
	 *            the messgae to set
	 */
	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the getter
	 */
	public String getGetter() {
		return getter;
	}

	/**
	 * @param getter
	 *            the getter to set
	 */
	public void setGetter(String getter) {
		this.getter = getter;
	}
}
