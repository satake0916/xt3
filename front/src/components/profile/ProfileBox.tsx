import React from 'react'
import "./ProfileBox.css"

type Props = {
  displayName: string | undefined,
  accountName: string | undefined,
  iconImageUrl: string | undefined,
  description: string | undefined,
}

const ProfileBox = ({displayName, accountName, iconImageUrl, description}: Props) => {
  return (
    <div className="profile-box">
      <div className="profile-box-header">
        <h2>
          {displayName}
        </h2>
        <p>{`@${accountName}`}</p>
      </div>

      <div className="profile-box-description">
        <p>{description}</p>
      </div>
    </div>
  )
}

export default ProfileBox;