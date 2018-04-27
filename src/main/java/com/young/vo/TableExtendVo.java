package com.young.vo;

import com.young.entity.TableCopy;
import com.young.entity.TableCopyCopy;
import com.young.entity.TableCopyCopyCopy;
import com.young.entity.User;

public class TableExtendVo {
    private User user;
    private TableCopy tableCopy;
    private TableCopyCopy tableCopyCopy;
    private TableCopyCopyCopy tableCopyCopyCopy;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TableCopy getTableCopy() {
        return tableCopy;
    }

    public void setTableCopy(TableCopy tableCopy) {
        this.tableCopy = tableCopy;
    }

    public TableCopyCopy getTableCopyCopy() {
        return tableCopyCopy;
    }

    public void setTableCopyCopy(TableCopyCopy tableCopyCopy) {
        this.tableCopyCopy = tableCopyCopy;
    }

    public TableCopyCopyCopy getTableCopyCopyCopy() {
        return tableCopyCopyCopy;
    }

    public void setTableCopyCopyCopy(TableCopyCopyCopy tableCopyCopyCopy) {
        this.tableCopyCopyCopy = tableCopyCopyCopy;
    }
}
