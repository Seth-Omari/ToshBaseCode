/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.chatsdk;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anthony Ngure on 04/10/2017.
 * Email : anthonyngure25@gmail.com.
 */

public class ConversationBuilder implements Parcelable {

    private String userName;
    private long userId;
    private String partnerName;
    private long partnerId;
    private String partnerAvatar;

    public ConversationBuilder() {
    }



    public String getUserName() {
        return userName;
    }

    public ConversationBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public ConversationBuilder withUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public ConversationBuilder withPartnerName(String partnerName) {
        this.partnerName = partnerName;
        return this;
    }

    public long getPartnerId() {
        return partnerId;
    }

    public ConversationBuilder withPartnerId(long partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public String getPartnerAvatar() {
        return partnerAvatar;
    }

    public ConversationBuilder withPartnerAvatar(String partnerAvatar) {
        this.partnerAvatar = partnerAvatar;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeLong(this.userId);
        dest.writeString(this.partnerName);
        dest.writeLong(this.partnerId);
        dest.writeString(this.partnerAvatar);
    }

    protected ConversationBuilder(Parcel in) {
        this.userName = in.readString();
        this.userId = in.readLong();
        this.partnerName = in.readString();
        this.partnerId = in.readLong();
        this.partnerAvatar = in.readString();
    }

    public static final Creator<ConversationBuilder> CREATOR = new Creator<ConversationBuilder>() {
        @Override
        public ConversationBuilder createFromParcel(Parcel source) {
            return new ConversationBuilder(source);
        }

        @Override
        public ConversationBuilder[] newArray(int size) {
            return new ConversationBuilder[size];
        }
    };

    public void startConversation(Activity activity) {
        ConversationActivity.start(activity, this);
    }

    @Override
    public String toString() {
        return "ConversationBuilder{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", partnerName='" + partnerName + '\'' +
                ", partnerId=" + partnerId +
                ", partnerAvatar='" + partnerAvatar + '\'' +
                '}';
    }
}

