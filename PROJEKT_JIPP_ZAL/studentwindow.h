#ifndef STUDENTWINDOW_H
#define STUDENTWINDOW_H

#include <QDialog>
#include "showbehavior.h"
#include "showgrades.h"

namespace Ui {
class StudentWindow;
}

class StudentWindow : public QDialog
{
    Q_OBJECT

public:
    explicit StudentWindow(QWidget *parent = nullptr);
    ~StudentWindow();

private slots:
    void on_exitPushButton_clicked();

    void on_gradesPushButton_clicked();

    void on_behaviorPushButton_clicked();

    void on_showDataPushButton_clicked();

private:
    Ui::StudentWindow *ui;
    ShowGrades *showGrades;
    ShowBehavior *showBehavior;
};

#endif // STUDENTWINDOW_H
