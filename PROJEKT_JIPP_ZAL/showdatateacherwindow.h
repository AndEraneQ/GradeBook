#ifndef SHOWDATATEACHERWINDOW_H
#define SHOWDATATEACHERWINDOW_H

#include "showgrades.h"
#include "showbehavior.h"
#include <QDialog>

namespace Ui {
class ShowDataTeacherWindow;
}

class ShowDataTeacherWindow : public QDialog
{
    Q_OBJECT

public:
    explicit ShowDataTeacherWindow(QWidget *parent = nullptr);
    ~ShowDataTeacherWindow();

private slots:
    void on_cancelPushButton_clicked();

    void on_nextPushButton_clicked();

private:
    Ui::ShowDataTeacherWindow *ui;
    ShowGrades *showGrades;
    ShowBehavior *showBehavior;
};

#endif // SHOWDATATEACHERWINDOW_H
