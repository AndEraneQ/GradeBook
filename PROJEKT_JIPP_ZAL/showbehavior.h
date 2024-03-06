#ifndef SHOWBEHAVIOR_H
#define SHOWBEHAVIOR_H

#include <QDialog>

namespace Ui {
class ShowBehavior;
}

class ShowBehavior : public QDialog
{
    Q_OBJECT

public:
    explicit ShowBehavior(QWidget *parent = nullptr);
    ~ShowBehavior();

private slots:
    void on_cancelPushButton_clicked();

private:
    Ui::ShowBehavior *ui;
};

#endif // SHOWBEHAVIOR_H
