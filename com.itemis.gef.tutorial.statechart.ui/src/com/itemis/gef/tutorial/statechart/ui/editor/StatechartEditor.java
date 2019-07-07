package com.itemis.gef.tutorial.statechart.ui.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.mvc.fx.ui.MvcFxUiModule;
import org.eclipse.gef.mvc.fx.ui.parts.AbstractFXEditor;
import org.eclipse.gef.mvc.fx.viewer.IViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

import com.google.inject.Guice;
import com.google.inject.util.Modules;
import com.itemis.gef.tutorial.statechart.StatechartModule;
import com.itemis.gef.tutorial.statechart.model.Statechart;

public class StatechartEditor extends AbstractFXEditor {

	public StatechartEditor() {
		super(Guice.createInjector(Modules.override(new StatechartModule()).with(new MvcFxUiModule())));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// retrieve the viewer's content
		IViewer viewer = getDomain().getAdapter(IViewer.class);
		Statechart statechart = (Statechart) viewer.getContents().get(0);

		try {
			// serialize statechart
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(statechart);
			oos.close();

			// write to file
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(new ByteArrayInputStream(out.toByteArray()), true, false, monitor);
			markNonDirty();
			firePropertyChange(PROP_DIRTY);
		} catch (IOException | CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		Statechart statechart = null;

		// read the given input file
		try {
			IFile file = ((FileEditorInput) input).getFile();
			ObjectInputStream is = new ObjectInputStream(file.getContents());
			statechart = (Statechart) is.readObject();
			is.close();
			setPartName(file.getName());
		} catch (EOFException e) {
			// create default statechart model
			statechart = Statechart.createExample();
		} catch (Exception e) {
			throw new PartInitException("Could not load input", e);
		}

		// populate tthe viewer
		if (statechart != null) {
			IViewer viewer = getDomain().getAdapter(IViewer.class);
			viewer.getContents().add(statechart);
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
