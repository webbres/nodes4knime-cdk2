/* Created on 25.03.2007 16:52:27 by thor
 * ------------------------------------------------------------------------
 *
 *  Copyright (C) 2003 - 2011
 *  University of Konstanz, Germany and
 *  KNIME GmbH, Konstanz, Germany
 *  Website: http://www.knime.org; Email: contact@knime.org
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License, Version 3, as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 *  Additional permission under GNU GPL version 3 section 7:
 *
 *  KNIME interoperates with ECLIPSE solely via ECLIPSE's plug-in APIs.
 *  Hence, KNIME and ECLIPSE are both independent programs and are not
 *  derived from each other. Should, however, the interpretation of the
 *  GNU GPL Version 3 ("License") under any applicable laws result in
 *  KNIME and ECLIPSE being a combined program, KNIME GMBH herewith grants
 *  you the additional permission to use and propagate KNIME together with
 *  ECLIPSE with only the license terms in place for ECLIPSE applying to
 *  ECLIPSE and the GNU GPL Version 3 applying for KNIME, provided the
 *  license terms of ECLIPSE themselves allow for the respective use and
 *  propagation of ECLIPSE together with KNIME.
 *
 *  Additional permission relating to nodes for KNIME that extend the Node
 *  Extension (and in particular that are based on subclasses of NodeModel,
 *  NodeDialog, and NodeView) and that only interoperate with KNIME through
 *  standard APIs ("Nodes"):
 *  Nodes are deemed to be separate and independent programs and to not be
 *  covered works.  Notwithstanding anything to the contrary in the
 *  License, the License does not apply to Nodes, you are not required to
 *  license Nodes under the License, and you are granted a license to
 *  prepare and propagate Nodes, in each case even if such Nodes are
 *  propagated with or for interoperation with KNIME.  The owner of a Node
 *  may freely choose the license terms applicable to such Node, including
 *  when such Node is propagated with or for interoperation with KNIME.
 * ------------------------------------------------------------------- *
 */
package org.openscience.cdk.knime;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This is the preference page for KNIME's CDK plugin.
 *
 * @author Thorsten Meinl, University of Konstanz
 */
public class CDKPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {
    /**
     * Creates a new preference page.
     */
    public CDKPreferencePage() {
        super(GRID);

        // we use the pref store of the UI plugin
        setPreferenceStore(CDKNodePlugin.getDefault().getPreferenceStore());
        setDescription("KNIME CDK preferences");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createFieldEditors() {
        Composite parent = getFieldEditorParent();

        BooleanFieldEditor showImplicitHydrogens =
            new BooleanFieldEditor(
                    CDKPreferenceInitializer.PREF_SHOW_IMPLICIT_HYDROGENS,
                    "Show implicit hydrogens in structure diagrams", parent);
        addField(showImplicitHydrogens);

        BooleanFieldEditor showExplicitHydrogens =
            new BooleanFieldEditor(
                    CDKPreferenceInitializer.PREF_SHOW_EXPLICIT_HYDROGENS,
                    "Show explicit hydrogens in structure diagrams", parent);
        addField(showExplicitHydrogens);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IWorkbench workbench) {
        // nothing to do
    }
}