package de.bht.fpa.mail.s769183.imapnavigation.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s769183.imapnavigation.Activator;

public class ImapLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Account) {
			return ((Account) element).getName();
		} else {
			return ((Folder) element).getFullName();
		}
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Account) {
			return AbstractUIPlugin.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID, "icons/xp_ppl09.png").createImage();
		} else {
			return AbstractUIPlugin.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID, "icons/Folder-Generic-Blue-icon.png")
					.createImage();
		}
	}
}
