#ifndef ADMINWINDOW_H
#define ADMINWINDOW_H
#include "registerwindow.h"
#include "removepersonwindow.h"
#include <Qdialog>

namespace Ui {
class AdminWindow;
}

class AdminWindow : public QDialog
{
    Q_OBJECT

public:
    explicit AdminWindow(QWidget *parent = nullptr);
    ~AdminWindow();

private slots:
    void on_logoutPushButton_clicked();

    void on_addPushButton_clicked();

    void on_deletePushButton_clicked();

private:
    Ui::AdminWindow *ui;
    RegisterWindow *registerWindow;
    RemovePersonWindow *removePersonWindow;
};

#endif // ADMINWINDOW_H
