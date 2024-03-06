#ifndef TEACHERWINDOW_H
#define TEACHERWINDOW_H

#include <QDialog>
#include "addgrades.h"
#include "addbehavio.h"
#include "teacherremovedata.h"
#include "showdatateacherwindow.h"
namespace Ui {
class TeacherWindow;
}

class TeacherWindow : public QDialog
{
    Q_OBJECT
public:
    explicit TeacherWindow(QWidget *parent = nullptr);
    ~TeacherWindow();
private slots:
    void on_gradePushButton_clicked();

    void on_logoutPushButton_clicked();

    void on_showStudentDataPushButton_clicked();

    void on_showDataPushButton_clicked();

    void on_deleteDataPushButton_clicked();

    void on_behaviorPushButton_clicked();

private:
    Ui::TeacherWindow *ui;
    AddGrades *addGrades;
    AddBehavio *addBehavio;
    TeacherRemoveData *teacherRemoveData;
    ShowDataTeacherWindow *showDataTeacherWindow;
};

#endif // TEACHERWINDOW_H


