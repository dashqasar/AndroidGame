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
package com.achep.base.permissions;

import android.content.Context;
import android.support.annotation.NonNull;

import com.achep.base.interfaces.IOnLowMemory;
import com.achep.base.interfaces.IPermission;

import java.util.ArrayList;

/**
 * Created by Artem Chepurnoy on 27.01.2015.
 */
public class PermissionGroup implements IOnLowMemory, IPermission {

    @NonNull
    public final Permission[] permissions;

    public PermissionGroup(@NonNull Permission[] permissions) {
        this.permissions = permissions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        for (Permission permission : permissions)
            if (!permission.isActive()) return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLowMemory() {
        for (Permission permission : permissions) {
            permission.onLowMemory();
        }
    }

    /**
     * @author Artem Chepurnoy
     */
    public static class Builder {

        @NonNull
        private final Context mContext;
        @NonNull
        private final ArrayList<String> mList;

        public Builder(@NonNull Context context) {
            mContext = context;
            mList = new ArrayList<>();
        }

        /**
         * Adds new permission to the group.
         *
         * @see com.achep.base.permissions.Permission#PERMISSION_ACCESSIBILITY
         * @see com.achep.base.permissions.Permission#PERMISSION_DEVICE_ADMIN
         * @see com.achep.base.permissions.Permission#PERMISSION_NOTIFICATION_LISTENER
         * @see com.achep.base.permissions.Permission#PERMISSION_USAGE_STATS
         */
        @NonNull
        public Builder add(@NonNull String permission) {
            mList.add(permission);
            return this;
        }

        @NonNull
        public PermissionGroup build() {
            final Permission[] p = new Permission[mList.size()];
            for (int i = 0; i < p.length; i++)
                p[i] = Permission.newInstance(mContext, mList.get(i));
            return new PermissionGroup(p);
        }

    }

}
