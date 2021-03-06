/*******************************************************************************
 * Copyright (C) 2013 Snowdream Mobile
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.snowdream.wallpaper.entity;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @date 2013-6-10
 * @version v1.0
 */
public class Albums extends Object implements Parcelable {

    @DatabaseField
    protected String name = null;

    @DatabaseField
    protected String created_by = null;

    @DatabaseField
    protected String uuid = null;

    @DatabaseField
    protected String created_at = null;

    @ForeignCollectionField(eager = false)
    protected Collection<Album> albums = null;

    public Albums() {
        super(TYPE_ALBUMS);
        // ORMLite needs a no-arg constructor
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the created_at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * @return the albums
     */
    public Collection<Album> getAlbums() {
        return albums;
    }

    /**
     * @param albums the albums to set
     */
    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(name);
        out.writeString(created_by);
        out.writeString(uuid);
        out.writeString(created_at);

        if (albums != null) {
            out.writeInt(albums.size());

            for (Album album : albums) {
                out.writeParcelable(album, flags);
            }
        }
    }

    public static final Parcelable.Creator<Albums> CREATOR = new Parcelable.Creator<Albums>() {
        public Albums createFromParcel(Parcel in) {
            return new Albums(in);
        }

        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };

    private Albums(Parcel in) {
        super(in);
        name = in.readString();
        created_by = in.readString();
        uuid = in.readString();
        created_at = in.readString();

        int count = in.readInt();
        if (count > 0) {
            albums = new ArrayList<Album>();
        }

        for (int i = 0; i < count; i++) {
            albums.add((Album) in.readParcelable(null));
        }
    }
}
