#ifndef TEACHERREMOVEDATA_H
#define TEACHERREMOVEDATA_H

#include <QDialog>

namespace Ui {
class TeacherRemoveData;
}

class TeacherRemoveData : public QDialog
{
    Q_OBJECT

public:
    explicit TeacherRemoveData(QWidget *parent = nullptr);
    ~TeacherRemoveData();
    void removeGradeUppdateData();
    void removeBehaviorUppdateData();

private slots:
    void on_cancelPushButton_clicked();

    void on_behaviorRadioButton_clicked();

    void on_gradeRadioButton_clicked();

    void on_deletePushButton_clicked();

    void on_subjectComboBox_currentIndexChanged();

    void on_personComboBox_currentIndexChanged();

private:
    Ui::TeacherRemoveData *ui;
};
#endif // TEACHERREMOVEDATA_H
