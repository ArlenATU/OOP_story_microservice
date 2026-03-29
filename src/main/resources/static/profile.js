const userId = 1;

let profileData = {};

window.onload = loadProfile;

function loadProfile() {

    fetch(`/api/profile/${userId}`)
        .then(res => {
            if (!res.ok) {
                throw new Error("Server error: " + res.status);
            }
            return res.json();
        })
        .then(data => {

            console.log("Loaded:", data);

            profileData = data.profile;

            document.getElementById("username").innerText = profileData.name || "User";
            document.getElementById("nameText").innerText = profileData.name || "No name";
            document.getElementById("bioText").innerText = profileData.bio || "No bio";

            const avatar = profileData.profilePictureUrl;

            const finalAvatar =
                avatar && avatar.trim() !== ""
                    ? avatar
                    : "/images/default-avatar.png";

            document.getElementById("avatarPreview").src = finalAvatar;
            document.getElementById("miniAvatar").src = finalAvatar;

            renderStories(data.stories || []);
        })
        .catch(err => {
            console.error("ERROR:", err);
            alert("Failed to load profile");
        });
}

function renderStories(stories) {

    const container = document.getElementById("storiesContainer");
    container.innerHTML = "";

    if (!stories || stories.length === 0) {
        container.innerHTML = "<p>No stories yet</p>";
        return;
    }

    stories.forEach(story => {

        const div = document.createElement("div");
        div.classList.add("story");

        div.innerHTML = `
            <h4>${story.title}</h4>
            <p>${story.content}</p>
        `;

        container.appendChild(div);
    });
}

function editName() {
    let value = prompt("Enter new name:", profileData.name);

    if (value) {
        profileData.name = value;
        document.getElementById("nameText").innerText = value;
        document.getElementById("username").innerText = value;
    }
}

function editBio() {
    let value = prompt("Enter new bio:", profileData.bio);

    if (value) {
        profileData.bio = value;
        document.getElementById("bioText").innerText = value;
    }
}

function uploadAvatar() {
    document.getElementById("avatarInput").click();
}

document.getElementById("avatarInput").addEventListener("change", function () {

    const file = this.files[0];
    if (!file) return;

    const reader = new FileReader();

    reader.onload = function (e) {

        // ✅ FIXED FIELD NAME
        profileData.profilePictureUrl = e.target.result;

        document.getElementById("avatarPreview").src = e.target.result;
        document.getElementById("miniAvatar").src = e.target.result;
    };

    reader.readAsDataURL(file);
});

function saveProfile() {

    fetch(`/api/profile/${userId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: profileData.name,
            bio: profileData.bio,
            profilePictureUrl: profileData.profilePictureUrl
        })
    })
        .then(res => {
            if (!res.ok) throw new Error("Save failed");
            alert("Profile updated");
            location.reload();
        })
        .catch(() => {
            alert("Error saving profile");
        });
}