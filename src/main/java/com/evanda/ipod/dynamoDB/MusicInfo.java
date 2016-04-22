package com.evanda.ipod.dynamoDB;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.mpatric.mp3agic.ID3v1;

/**
 * A Music File Information. 
 * The 'uuid' attribute is a value generated by the hashCode() method.
 * 
 * @author anayak
 *
 */
@DynamoDBTable(tableName = "MusicInfo")
public class MusicInfo {
	
	public MusicInfo() {
		super();
	}

	private String fileName;
	
	private double fileLengthInSeconds;
	
	private double bitrate;
	
	private double sampleRate;
	
	private boolean isID3v1TagPresent;
	
	private boolean isCustomTagPresent;
	
	private String systemComments;
	
	@DynamoDBRangeKey(attributeName="createdTime")
	private String fileUploadedTime;
	
	@DynamoDBHashKey(attributeName="uuid")
	private int uuid;
	
	//info from id3v1 tags
	private String track;
	
	private String artist;
	
	private String title;
	
	private String album;
	
	private String year;
	
	private String genre;
	
	private String comment;
	
	//ID3v1 tag 
	private ID3v1 id3v1Tag;
	
	private boolean isProcessed;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getFileLengthInSeconds() {
		return fileLengthInSeconds;
	}

	public void setFileLengthInSeconds(double fileLengthInSeconds) {
		this.fileLengthInSeconds = fileLengthInSeconds;
	}

	public double getBitrate() {
		return bitrate;
	}

	public void setBitrate(double bitrate) {
		this.bitrate = bitrate;
	}

	public double getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(double sampleRate) {
		this.sampleRate = sampleRate;
	}

	public boolean isID3v1TagPresent() {
		return isID3v1TagPresent;
	}

	public void setID3v1TagPresent(boolean isID3v1TagPresent) {
		this.isID3v1TagPresent = isID3v1TagPresent;
	}

	public boolean isCustomTagPresent() {
		return isCustomTagPresent;
	}

	public void setCustomTagPresent(boolean isCustomTagPresent) {
		this.isCustomTagPresent = isCustomTagPresent;
	}

	public ID3v1 getId3v1Tag() {
		return id3v1Tag;
	}

	public void setId3v1Tag(ID3v1 id3v1Tag) {
		this.id3v1Tag = id3v1Tag;
	}

	public String getFileUploadedTime() {
		return fileUploadedTime;
	}

	public void setFileUploadedTime(String fileUploadedTime) {
		this.fileUploadedTime = fileUploadedTime;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the systemComments
	 */
	public String getSystemComments() {
		return systemComments;
	}

	/**
	 * @param systemComments the systemComments to set
	 */
	public void setSystemComments(String systemComments) {
		this.systemComments = systemComments;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bitrate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(fileLengthInSeconds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + (isID3v1TagPresent ? 1231 : 1237);
		temp = Double.doubleToLongBits(sampleRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MusicInfo))
			return false;
		MusicInfo other = (MusicInfo) obj;
		if (Double.doubleToLongBits(bitrate) != Double.doubleToLongBits(other.bitrate))
			return false;
		if (Double.doubleToLongBits(fileLengthInSeconds) != Double.doubleToLongBits(other.fileLengthInSeconds))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileUploadedTime == null) {
			if (other.fileUploadedTime != null)
				return false;
		} else if (!fileUploadedTime.equals(other.fileUploadedTime))
			return false;
		if (isID3v1TagPresent != other.isID3v1TagPresent)
			return false;
		if (Double.doubleToLongBits(sampleRate) != Double.doubleToLongBits(other.sampleRate))
			return false;
		return true;
	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param fileName
	 * @param fileLengthInSeconds
	 * @param bitrate
	 * @param sampleRate
	 * @param isID3v1TagPresent
	 * @param isCustomTagPresent
	 * @param fileUploadedTime
	 */
	public MusicInfo(String fileName, double fileLengthInSeconds, double bitrate, double sampleRate,
			boolean isID3v1TagPresent, boolean isCustomTagPresent, String fileUploadedTime) {
		super();
		this.fileName = fileName;
		this.fileLengthInSeconds = fileLengthInSeconds;
		this.bitrate = bitrate;
		this.sampleRate = sampleRate;
		this.isID3v1TagPresent = isID3v1TagPresent;
		this.isCustomTagPresent = isCustomTagPresent;
		this.fileUploadedTime = fileUploadedTime;
	}

	/**
	 * @return the isProcessed
	 */
	public boolean isProcessed() {
		return isProcessed;
	}

	/**
	 * @param isProcessed the isProcessed to set
	 */
	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	/**
	 * @return the track
	 */
	public String getTrack() {
		return track;
	}

	/**
	 * @param track the track to set
	 */
	public void setTrack(String track) {
		this.track = track;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MusicInfo [fileName=" + fileName + ", fileLengthInSeconds=" + fileLengthInSeconds + ", bitrate="
				+ bitrate + ", sampleRate=" + sampleRate + ", systemComments=" + systemComments + ", fileUploadedTime="
				+ fileUploadedTime + ", uuid=" + uuid + ", track=" + track + ", artist=" + artist + ", title=" + title
				+ ", album=" + album + ", year=" + year + ", genre=" + genre + ", comment=" + comment + ", id3v1Tag="
				+ id3v1Tag + ", isProcessed=" + isProcessed + "]";
	}
	

}
