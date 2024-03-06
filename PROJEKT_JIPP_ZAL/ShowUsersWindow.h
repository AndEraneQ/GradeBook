#ifndef SHOWUSERSWINDOW_H
#define SHOWUSERSWINDOW_H

#include <QDialog>

namespace Ui {
class ShowUsersWindow;
}

class ShowUsersWindow : public QDialog
{
    Q_OBJECT

public:
    explicit ShowUsersWindow(QWidget *parent = nullptr);
    ~ShowUsersWindow();

private:
    Ui::ShowUsersWindow *ui;
};

#endif // SHOWUSERSWINDOW_H
