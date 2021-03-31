/**
 * 
 */
package DataSorter.Filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Itay Bar Nissim
 *
 */
public class Extension {
	private ExtensionList extensionsList;
	private int extensionPointer;
	private String name;

	public Extension(String extention) {
		extensionsList = new ExtensionList();
		this.name = extention;
		add(name);
		extensionsList = new ExtensionList();
		setPointer(extensionsList.getExtensions().indexOf(this));
	}

	public void add(String extention) {
		boolean createList = check(extention);
		if (createList == true) {
			extensionsList.getExtensions().add(this);
			extensionsList.getIsAdded().add(false);
		}
	}

	public boolean check(String extention) {
		Iterator<Extension> itr = extensionsList.getExtensions().iterator();
		while (itr.hasNext()) {
			if (itr.next().name == extention)
				return false;

		}
		return true;
	}

	public void setPointer(int pointer) {
		extensionPointer = pointer;
	}

	public int getExtensionPointer() {
		return extensionPointer;
	}

	public ExtensionList ExtensionList() {
		return extensionsList;
	}

	public ExtensionList getExtensionsList() {
		return extensionsList;
	}

	public void setExtensionsList(ExtensionList extensionsList) {
		this.extensionsList = extensionsList;
	}

	class ExtensionList {
		private ArrayList<Extension> extensions;
		private Extension current;
		private ArrayList<Boolean> isAdded;

		public ExtensionList() {
			extensions = new ArrayList<Extension>();
			isAdded = new ArrayList<Boolean>();
		}

		public void addListExtension(String extention) {

			isAdded.add(extensionPointer, true);
		}

		public boolean isAddedToList() {
			return this.isAdded.get(extensionPointer);
		}

		public ArrayList<Extension> getExtensions() {
			return extensions;
		}

		public void setExtensions(ArrayList<Extension> extensions) {
			this.extensions = extensions;
		}

		public Extension getCurrent() {
			return current;
		}

		public void setCurrent(Extension current) {
			this.current = current;
		}

		public List<Boolean> getIsAdded() {
			return isAdded;
		}

		public void setIsAdded(ArrayList<Boolean> isAdded) {
			this.isAdded = isAdded;
		}

	}

}
