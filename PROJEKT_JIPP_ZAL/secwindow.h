#ifndef SECWINDOW_H
#define SECWINDOW_H

#include <QDialog>

namespace Ui {
class secWindow;
}

class secWindow : public QDialog
{
    Q_OBJECT

public:
    explicit secWindow(QWidget *parent = nullptr);
    ~secWindow();

private:
    Ui::secWindow *ui;
};

#endif // SECWINDOW_H
