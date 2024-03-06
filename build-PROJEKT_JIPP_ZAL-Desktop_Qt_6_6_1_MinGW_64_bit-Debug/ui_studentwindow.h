/********************************************************************************
** Form generated from reading UI file 'studentwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_STUDENTWINDOW_H
#define UI_STUDENTWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_StudentWindow
{
public:
    QVBoxLayout *verticalLayout_2;
    QHBoxLayout *horizontalLayout_2;
    QHBoxLayout *horizontalLayout;
    QLabel *personLabel;
    QLabel *nameLabel;
    QLabel *lastNameLabel;
    QSpacerItem *horizontalSpacer;
    QPushButton *showDataPushButton;
    QVBoxLayout *verticalLayout;
    QPushButton *gradesPushButton;
    QPushButton *behaviorPushButton;
    QPushButton *exitPushButton;

    void setupUi(QDialog *StudentWindow)
    {
        if (StudentWindow->objectName().isEmpty())
            StudentWindow->setObjectName("StudentWindow");
        StudentWindow->resize(400, 250);
        verticalLayout_2 = new QVBoxLayout(StudentWindow);
        verticalLayout_2->setObjectName("verticalLayout_2");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        personLabel = new QLabel(StudentWindow);
        personLabel->setObjectName("personLabel");
        personLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(personLabel);

        nameLabel = new QLabel(StudentWindow);
        nameLabel->setObjectName("nameLabel");
        nameLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(nameLabel);

        lastNameLabel = new QLabel(StudentWindow);
        lastNameLabel->setObjectName("lastNameLabel");
        lastNameLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(lastNameLabel);


        horizontalLayout_2->addLayout(horizontalLayout);

        horizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer);

        showDataPushButton = new QPushButton(StudentWindow);
        showDataPushButton->setObjectName("showDataPushButton");
        showDataPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        showDataPushButton->setStyleSheet(QString::fromUtf8("font: 700 12pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(showDataPushButton);


        verticalLayout_2->addLayout(horizontalLayout_2);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        gradesPushButton = new QPushButton(StudentWindow);
        gradesPushButton->setObjectName("gradesPushButton");
        gradesPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        gradesPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(gradesPushButton);

        behaviorPushButton = new QPushButton(StudentWindow);
        behaviorPushButton->setObjectName("behaviorPushButton");
        behaviorPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        behaviorPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(behaviorPushButton);

        exitPushButton = new QPushButton(StudentWindow);
        exitPushButton->setObjectName("exitPushButton");
        exitPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        exitPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(exitPushButton);


        verticalLayout_2->addLayout(verticalLayout);

        verticalLayout_2->setStretch(0, 1);
        verticalLayout_2->setStretch(1, 2);

        retranslateUi(StudentWindow);

        QMetaObject::connectSlotsByName(StudentWindow);
    } // setupUi

    void retranslateUi(QDialog *StudentWindow)
    {
        StudentWindow->setWindowTitle(QCoreApplication::translate("StudentWindow", "Dialog", nullptr));
        personLabel->setText(QCoreApplication::translate("StudentWindow", "Osoba: ", nullptr));
        nameLabel->setText(QString());
        lastNameLabel->setText(QString());
        showDataPushButton->setText(QCoreApplication::translate("StudentWindow", "Pokaz  moje dane", nullptr));
        gradesPushButton->setText(QCoreApplication::translate("StudentWindow", "Pokaz oceny", nullptr));
        behaviorPushButton->setText(QCoreApplication::translate("StudentWindow", "Pokaz zachowanie", nullptr));
        exitPushButton->setText(QCoreApplication::translate("StudentWindow", "Wyloguj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class StudentWindow: public Ui_StudentWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_STUDENTWINDOW_H
