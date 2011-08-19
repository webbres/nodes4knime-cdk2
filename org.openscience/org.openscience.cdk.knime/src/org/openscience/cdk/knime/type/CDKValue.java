/*
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
 * -------------------------------------------------------------------
 *
 */
package org.openscience.cdk.knime.type;

import javax.swing.Icon;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataValue;
import org.knime.core.data.DataValueComparator;
import org.knime.core.data.renderer.DataValueRendererFamily;
import org.knime.core.data.renderer.DefaultDataValueRendererFamily;
import org.knime.core.data.renderer.MultiLineStringValueRenderer;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecule;

/**
 * DataValue for a Molecule.
 *
 * @author Bernd Wiswedel, University of Konstanz
 */
public interface CDKValue extends DataValue {

    /**
     * Meta information to this value type.
     *
     * @see DataValue#UTILITY
     */
    public static final UtilityFactory UTILITY = new CDKUtilityFactory();

    /**
     * Get the CDK molecule to the smiles.
     *
     * @return the corresponding molecule
     * @deprecated use {@link #getAtomContainer()} instead
     */
    @Deprecated
    IMolecule getMolecule();


    /**
     * Get the CDK atom container.
     *
     * @return the corresponding atom container
     */
    IAtomContainer getAtomContainer();

    /** Implementations of the meta information of this value class. */
    public static class CDKUtilityFactory extends UtilityFactory {
        /** Singleton icon to be used to display this cell type. */
        private static final Icon ICON = loadIcon(CDKValue.class, "/cdk.png");

        private static final DataValueComparator COMPARATOR =
                new DataValueComparator() {
                    @Override
                    protected int compareDataValues(final DataValue v1,
                            final DataValue v2) {
                        int atomCount1 =
                                ((CDKValue)v1).getAtomContainer().getAtomCount();
                        int atomCount2 =
                                ((CDKValue)v2).getAtomContainer().getAtomCount();
                        return atomCount1 - atomCount2;
                    }
                };

        /** Only subclasses are allowed to instantiate this class. */
        protected CDKUtilityFactory() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Icon getIcon() {
            return ICON;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected DataValueComparator getComparator() {
            return COMPARATOR;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected DataValueRendererFamily getRendererFamily(
                final DataColumnSpec spec) {
             return new DefaultDataValueRendererFamily(new CDKValueRenderer(),
             new MultiLineStringValueRenderer("String"));
//            return new DefaultDataValueRendererFamily(
//                    new MultiLineStringValueRenderer("String"));
        }
    }
}