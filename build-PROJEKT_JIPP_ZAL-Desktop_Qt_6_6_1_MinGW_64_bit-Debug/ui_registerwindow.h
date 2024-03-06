/********************************************************************************
** Form generated from reading UI file 'registerwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_REGISTERWINDOW_H
#define UI_REGISTERWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_RegisterWindow
{
public:
    QVBoxLayout *verticalLayout_6;
    QVBoxLayout *verticalLayout_4;
    QVBoxLayout *verticalLayout_3;
    QLabel *enterDataLabel;
    QHBoxLayout *horizontalLayout;
    QVBoxLayout *verticalLayout;
    QLabel *nameLabel;
    QLabel *lastNameLabel;
    QLabel *emailLabel;
    QLabel *passwordLabel;
    QVBoxLayout *verticalLayout_2;
    QLineEdit *nameLineEdit;
    QLineEdit *lastNameLineEdit;
    QLineEdit *emailLineEdit;
    QLineEdit *passwordLineEdit;
    QHBoxLayout *horizontalLayout_2;
    QLabel *roleLabel;
    QRadioButton *teacherRadioButton;
    QRadioButton *studentRadioButton;
    QHBoxLayout *horizontalLayout_3;
    QLabel *photoLabel;
    QPushButton *cancelPushButton;
    QPushButton *registerPushButton;

    void setupUi(QDialog *RegisterWindow)
    {
        if (RegisterWindow->objectName().isEmpty())
            RegisterWindow->setObjectName("RegisterWindow");
        RegisterWindow->resize(849, 414);
        RegisterWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_6 = new QVBoxLayout(RegisterWindow);
        verticalLayout_6->setObjectName("verticalLayout_6");
        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4->setObjectName("verticalLayout_4");
        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setObjectName("verticalLayout_3");
        enterDataLabel = new QLabel(RegisterWindow);
        enterDataLabel->setObjectName("enterDataLabel");
        enterDataLabel->setStyleSheet(QString::fromUtf8("font: 700 26pt \"Segoe UI\";\n"
"color: \"white\";"));

        verticalLayout_3->addWidget(enterDataLabel);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        nameLabel = new QLabel(RegisterWindow);
        nameLabel->setObjectName("nameLabel");
        nameLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(nameLabel);

        lastNameLabel = new QLabel(RegisterWindow);
        lastNameLabel->setObjectName("lastNameLabel");
        lastNameLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(lastNameLabel);

        emailLabel = new QLabel(RegisterWindow);
        emailLabel->setObjectName("emailLabel");
        emailLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(emailLabel);

        passwordLabel = new QLabel(RegisterWindow);
        passwordLabel->setObjectName("passwordLabel");
        passwordLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(passwordLabel);


        horizontalLayout->addLayout(verticalLayout);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName("verticalLayout_2");
        nameLineEdit = new QLineEdit(RegisterWindow);
        nameLineEdit->setObjectName("nameLineEdit");
        nameLineEdit->setStyleSheet(QString::fromUtf8("font: 14pt \"Segoe UI\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(nameLineEdit);

        lastNameLineEdit = new QLineEdit(RegisterWindow);
        lastNameLineEdit->setObjectName("lastNameLineEdit");
        lastNameLineEdit->setStyleSheet(QString::fromUtf8("font: 14pt \"Segoe UI\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(lastNameLineEdit);

        emailLineEdit = new QLineEdit(RegisterWindow);
        emailLineEdit->setObjectName("emailLineEdit");
        emailLineEdit->setStyleSheet(QString::fromUtf8("font: 14pt \"Segoe UI\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(emailLineEdit);

        passwordLineEdit = new QLineEdit(RegisterWindow);
        passwordLineEdit->setObjectName("passwordLineEdit");
        passwordLineEdit->setStyleSheet(QString::fromUtf8("font: 14pt \"Segoe UI\";\n"
"color: \"white\";"));
        passwordLineEdit->setEchoMode(QLineEdit::Password);

        verticalLayout_2->addWidget(passwordLineEdit);


        horizontalLayout->addLayout(verticalLayout_2);


        verticalLayout_3->addLayout(horizontalLayout);


        verticalLayout_4->addLayout(verticalLayout_3);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        roleLabel = new QLabel(RegisterWindow);
        roleLabel->setObjectName("roleLabel");
        roleLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(roleLabel);

        teacherRadioButton = new QRadioButton(RegisterWindow);
        teacherRadioButton->setObjectName("teacherRadioButton");
        teacherRadioButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(teacherRadioButton);

        studentRadioButton = new QRadioButton(RegisterWindow);
        studentRadioButton->setObjectName("studentRadioButton");
        studentRadioButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));
        studentRadioButton->setAutoRepeatDelay(200);

        horizontalLayout_2->addWidget(studentRadioButton);


        verticalLayout_4->addLayout(horizontalLayout_2);


        verticalLayout_6->addLayout(verticalLayout_4);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        photoLabel = new QLabel(RegisterWindow);
        photoLabel->setObjectName("photoLabel");

        horizontalLayout_3->addWidget(photoLabel);

        cancelPushButton = new QPushButton(RegisterWindow);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));
        cancelPushButton->setAutoRepeatDelay(200);

        horizontalLayout_3->addWidget(cancelPushButton);

        registerPushButton = new QPushButton(RegisterWindow);
        registerPushButton->setObjectName("registerPushButton");
        registerPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        registerPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));
        registerPushButton->setAutoRepeatDelay(200);

        horizontalLayout_3->addWidget(registerPushButton);


        verticalLayout_6->addLayout(horizontalLayout_3);


        retranslateUi(RegisterWindow);

        QMetaObject::connectSlotsByName(RegisterWindow);
    } // setupUi

    void retranslateUi(QDialog *RegisterWindow)
    {
        RegisterWindow->setWindowTitle(QCoreApplication::translate("RegisterWindow", "Dialog", nullptr));
        enterDataLabel->setText(QCoreApplication::translate("RegisterWindow", "Podaj Dane:", nullptr));
        nameLabel->setText(QCoreApplication::translate("RegisterWindow", "Imie:", nullptr));
        lastNameLabel->setText(QCoreApplication::translate("RegisterWindow", "Nazwisko:", nullptr));
        emailLabel->setText(QCoreApplication::translate("RegisterWindow", "E-mail:", nullptr));
        passwordLabel->setText(QCoreApplication::translate("RegisterWindow", "Has\305\202o:", nullptr));
        roleLabel->setText(QCoreApplication::translate("RegisterWindow", "Rola:", nullptr));
        teacherRadioButton->setText(QCoreApplication::translate("RegisterWindow", "Nauczyciel", nullptr));
        studentRadioButton->setText(QCoreApplication::translate("RegisterWindow", "Ucze\305\204", nullptr));
        photoLabel->setText(QString());
        cancelPushButton->setText(QCoreApplication::translate("RegisterWindow", "Anuluj", nullptr));
        registerPushButton->setText(QCoreApplication::translate("RegisterWindow", "Rejestruj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class RegisterWindow: public Ui_RegisterWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_REGISTERWINDOW_H
