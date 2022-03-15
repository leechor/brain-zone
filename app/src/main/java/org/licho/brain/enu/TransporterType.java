package org.licho.brain.enu;

/**
 * This enumeration is found in the <i>Transporter Type</i> property of the <i>Ride Step</i> and therefore also in the
 * standard TransferNode since this object uses a Ride Step in its logic. This property specifies where to look to reserve
 * a transporter to ride on - either to a specific transporter or select from a list of transporters.
 */
public enum TransporterType {
    SPECIFIC,
    FROM_LIST,
}
