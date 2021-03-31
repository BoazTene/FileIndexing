package DataSorter;

import DataSorter.Filters.Extension;

public class MyFile {
		private String name;
		private Extension extension;
		private boolean isfolder;
		private String creationTime;
		
		public MyFile(String name, Extension extension, boolean isfolder) {
			this.name = name;
			this.extension = extension;
			this.isfolder = isfolder;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Extension getExtension() {
			return extension;
		}

		public void setExtension(Extension extension) {
			this.extension = extension;
		}

		public boolean isIsfolder() {
			return isfolder;
		}

		public void setIsfolder(boolean isfolder) {
			this.isfolder = isfolder;
		}
		public String getCreationTime() {
			return this.creationTime;
		}
		public void creationTime(String creationTime) {
			this.creationTime = creationTime;
		}
		
	}

