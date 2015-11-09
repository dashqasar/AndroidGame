/*
 * Copyright (C) 2015 AChep@xda <artemchep@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package com.achep.base.tests;

import android.support.annotation.Nullable;

/**
 * Created by Artem Chepurnoy on 21.01.2015.
 */
public abstract class Check {

    private static Check sCheck;

    public static Check getInstance() {
        if (sCheck == null) {
            sCheck = new CheckImpl();
        }
        return sCheck;
    }

    public abstract void isTrue(boolean bool);

    public abstract void isFalse(boolean bool);

    public abstract void isNull(@Nullable Object object);

    public abstract void isNonNull(@Nullable Object object);

    public abstract void isInMainThread();
}